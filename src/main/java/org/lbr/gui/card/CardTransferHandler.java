package org.lbr.gui.card;

import org.lbr.GameEngine;
import org.lbr.gameobject.GameObject;
import org.lbr.gameobject.cultivable.Cultivable;
import org.lbr.gameobject.cultivable.animal.Animal;
import org.lbr.gameobject.item.Destroy;
import org.lbr.gameobject.item.InstantHarvest;
import org.lbr.gameobject.item.Item;
import org.lbr.gameobject.product.Product;
import org.lbr.gui.MainWindow;

import javax.swing.*;
import javax.swing.text.html.HTML.Tag;

import java.awt.datatransfer.*;
import java.io.IOException;

public class CardTransferHandler extends TransferHandler {
    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        try {
            //return null;

            Card card = (Card) c;
            boolean canTransf = ((MainWindow) card.getParent().getParent().getParent()).canTransfer();
            if (!canTransf) {
                return null;
            }
            GameObject gameObject = card.getGameObject();
            return new GameObjectTransferable(gameObject);
        } catch (Exception e) {
            Card card = (Card) c;
            boolean canTransf = ((MainWindow) card.getParent().getParent().getParent().getParent()).canTransfer();
            if (!canTransf) {
                return null;
            }
            GameObject gameObject = card.getGameObject();
            return new GameObjectTransferable(gameObject);
        }
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        return support.isDataFlavorSupported(GameObjectTransferable.GAME_OBJECT_FLAVOR);
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        try {
            Card targetCard = (Card) support.getComponent();
            GameObject droppedGameObject = (GameObject) support.getTransferable()
                    .getTransferData(GameObjectTransferable.GAME_OBJECT_FLAVOR);
            if (droppedGameObject == null) {
                return false;
            }
            Card sourceCard = droppedGameObject.getParent();

            // Check if the source is also the target card
            if (sourceCard.equals(targetCard)) {
                return false;
            }
            System.out.println("SOURCE: " + sourceCard.getCol() + " TARGET: " + targetCard.getCol());

            if (sourceCard.getCurrentPosition() == Card.FIELD && targetCard.getCurrentPosition() == Card.DECK) {
                return false;
            }
            if (sourceCard.getCurrentPosition() == Card.SHOP && targetCard.getCurrentPosition() == Card.DECK) {
            	System.out.println("HEREAA");
            	if (targetCard.getGameObject()  != null) {
            		return false;
            	}
            	try {
            		System.out.println("VBECTOR");
	            	GameObject sourceGameObject = sourceCard.getGameObject();
	            	System.out.println(sourceGameObject.getName().toUpperCase().replace(' ', '_'));
	            	Product product = new Product(sourceGameObject.getName().toUpperCase().replace(' ', '_'));
	            	System.out.println("GOING");
	            	((MainWindow)targetCard.getParent().getParent().getParent()).buyProduct(product, targetCard.getCol());
	            	sourceCard.buyHappened(-1);
	            	System.out.println("SAYANG");
	            	System.out.println(product.getName());
	            	targetCard.setGameObject(product);
	            	
	            	return true;
            	} catch (Exception e) {
					return false;
				}
            	
            }
            if (sourceCard.getCurrentPosition() == Card.DECK && targetCard.getCurrentPosition() == Card.SHOP) {
            	if(sourceCard.getGameObject().getName() != targetCard.getGameObject().getName()) {
            		return false;
            	}
            	Product product = new Product(targetCard.getGameObject().getName().toUpperCase().replace(' ', '_'));
            	((MainWindow)sourceCard.getParent().getParent().getParent()).sellProduct(product, sourceCard.getCol());
            	targetCard.buyHappened(1);
            	sourceCard.setGameObject(null);
//                ((MainWindow)sourceCard.getParent().getParent().getParent()).updatePlayerHandDisplay();
            	return true;
            }
            if (sourceCard.getCurrentPosition() == Card.SHOP && targetCard.getCurrentPosition() == Card.SHOP) {
            	return false;
            }
            
            if (sourceCard.getCurrentPosition() == Card.DECK && targetCard.getCurrentPosition() == Card.DECK) {
            	GameObject targetGameObject = targetCard.getGameObject();
            	((MainWindow)sourceCard.getParent().getParent().getParent()).swapDeck(targetCard.getCol(), sourceCard.getCol());
                targetCard.setGameObject(droppedGameObject);
                sourceCard.setGameObject(targetGameObject);  // swapping objects
                return true;
            }
            
            if (sourceCard.getCurrentPosition() == Card.DECK && targetCard.getCurrentPosition() == Card.FIELD) {
            	if (sourceCard.getGameObject() instanceof Product || sourceCard.getGameObject() instanceof Item ) {
            		if (targetCard.getGameObject() == null) {
            			return false;
            		}
            		System.out.println("DEBUG1");
            		if (sourceCard.getGameObject() instanceof Product) {
            			if (!(targetCard.getGameObject() instanceof Animal)) {
            				return false;
            			}
            			boolean canDrop = ((MainWindow)(sourceCard.getParent().getParent().getParent())).productDrop(targetCard.getOwner(), (Product) sourceCard.getGameObject(), (Animal) targetCard.getGameObject(), sourceCard.getCol());
            			if (!canDrop) {
            				return false;
            			}
            			sourceCard.setGameObject(null);
                        targetCard.setGameObject(targetCard.getGameObject());
            			return true;
            		} else {
                        GameObject srcGameObject = sourceCard.getGameObject();
            			boolean canDrop = ((MainWindow)(sourceCard.getParent().getParent().getParent())).itemDrop(targetCard.getOwner(), (Item) sourceCard.getGameObject(), (Cultivable) targetCard.getGameObject(), sourceCard.getCol(), targetCard.getRow(), targetCard.getCol());
            			System.out.println("THING: " + Integer.toString(targetCard.getOwner().getGulden()));
            			if (!canDrop) {
            				System.out.println("CANDROPFALSE");
            				return false;
            			}
                        System.out.println("MASUK ITEM DROP");
                        if (srcGameObject instanceof InstantHarvest) {
                            System.out.println("INSTANT HARVEST");
                            targetCard.setGameObject(null);
                        } else if (srcGameObject instanceof Destroy) {
                            targetCard.setGameObject(null);
                            sourceCard.setGameObject(null);
                        } else {
                            sourceCard.setGameObject(null);
                            targetCard.setGameObject(targetCard.getGameObject());
                        }

                        return true;
            		}
            	} else {
            		if (targetCard.getGameObject() != null ) {
            			return false;
            		}
            		if (!targetCard.getOwner().equals(sourceCard.getOwner())) {
            			return false;
            		}
                	GameObject targetGameObject = targetCard.getGameObject();
            		((MainWindow)sourceCard.getParent().getParent().getParent()).from_deck_to_field(sourceCard.getCol(), targetCard.getRow(), targetCard.getCol());


            		targetCard.setGameObject(droppedGameObject);
                    sourceCard.setGameObject(targetGameObject); 
                    System.out.println("YA");
                    return true;
            	}
            }
            
            if (sourceCard.getCurrentPosition() == Card.FIELD && targetCard.getCurrentPosition() == Card.FIELD) {
            	GameObject targetGameObject = targetCard.getGameObject();
        		((MainWindow)sourceCard.getParent().getParent().getParent().getParent()).swapField(sourceCard.getRow(), sourceCard.getCol(), targetCard.getRow(), targetCard.getCol());


        		targetCard.setGameObject(droppedGameObject);
                sourceCard.setGameObject(targetGameObject); 
                System.out.println("FIELD TO FIELD");
                return true;
            }
            
            GameObject targetGameObject = targetCard.getGameObject();
            targetCard.setGameObject(droppedGameObject);
            sourceCard.setGameObject(targetGameObject);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

class GameObjectTransferable implements Transferable {
    static final DataFlavor GAME_OBJECT_FLAVOR = new DataFlavor(GameObject.class, "GameObject");
    private static final DataFlavor[] SUPPORTED_FLAVORS = { GAME_OBJECT_FLAVOR };
    private final GameObject gameObject;

    public GameObjectTransferable(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return GAME_OBJECT_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (GAME_OBJECT_FLAVOR.equals(flavor)) {
            return gameObject;
        }
        throw new UnsupportedFlavorException(flavor);
    }
}