package org.idmef;

class IDMEFObjectAdaptException extends Exception {
    public IDMEFObjectAdaptException(String message) {
        super(message);
    }
}

interface IDMEFObjectAdapter {
    IDMEFObject convert(Object o) throws IDMEFObjectAdaptException;
}
