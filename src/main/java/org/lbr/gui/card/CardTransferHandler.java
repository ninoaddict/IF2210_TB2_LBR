package org.lbr.gui.card;

import org.lbr.gameobject.GameObject;

import javax.swing.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class CardTransferHandler extends TransferHandler {
    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        Card card = (Card) c;
        GameObject gameObject = card.getGameObject();
        return new GameObjectTransferable(gameObject);
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
            System.out.println(sourceCard.getRow() + " " + sourceCard.getCol());

            if (sourceCard.getCurrentPosition() == Card.FIELD && targetCard.getCurrentPosition() == Card.DECK) {
                return false;
            }

            GameObject targetGameObject = targetCard.getGameObject();
            targetCard.setGameObject(droppedGameObject);
            sourceCard.setGameObject(targetGameObject);  // Explicitly set to null

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