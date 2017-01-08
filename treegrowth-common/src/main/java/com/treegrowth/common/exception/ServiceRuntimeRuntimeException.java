package com.treegrowth.common.exception;

public abstract class ServiceRuntimeRuntimeException extends CustomRuntimeException {

    private static final long serialVersionUID = -2746212161416739691L;

    public ServiceRuntimeRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeRuntimeException(Enum<?> message) {
        super(message);
    }

    @Override
    protected final String baseName() {
        return "exception.service";
    }

    @Override
    protected String module() {
        return String.format("SRV_%s",modulePart());
    }

    protected abstract String modulePart();
}
