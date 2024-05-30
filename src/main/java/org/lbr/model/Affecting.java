package org.lbr.model;

public interface Affecting {
    public abstract void runEffect(Cultivable cultivable) throws Exception;
}
