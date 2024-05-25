package org.lbr.gameobject.item;

import org.lbr.gameobject.cultivable.Cultivable;

public interface Affecting {
    public abstract void runEffect(Cultivable cultivable) throws Exception;
}
