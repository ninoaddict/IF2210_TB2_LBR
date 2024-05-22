package org.lbr;

import javax.swing.*;
import java.awt.datatransfer.*;
import java.io.IOException;

class CardTransferHandler extends TransferHandler {
    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
    	GameObject uwuGameObject = ((Card) c).getGameObject();
    	int r = ((Card) c).getRow();
    	int m = ((Card) c).getCol();
    	if(uwuGameObject == null) {
    		System.out.println("Picking null value!");
    	}
        return new GameObjectTransferable(uwuGameObject);
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
            	System.out.println("Dropped null");
                return false;
            }
            Card sourceCard = droppedGameObject.getParent();

            // check if the source is also the target card
            if (sourceCard.equals(targetCard)) {
                return false;
            }

            if (sourceCard.getCurrentPosition() == Card.FIELD && targetCard.getCurrentPosition() == Card.DECK) {
                return false;
            }

            GameObject temp = targetCard.getGameObject();
            targetCard.setGameObject(droppedGameObject);
            sourceCard.setGameObject(temp);
            if(targetCard.getGameObject() == null) {
            	System.out.println("Target card null");
            }
            if(sourceCard.getGameObject() == null) {
            	System.out.println("Source card null");
            }
            if(targetCard.getGameObject().equals(sourceCard.getGameObject())) {
            	System.out.println("WUh");
            }
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
    	if(gameObject == null) {
    		System.out.println("AWAUNUL");
    	}
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
