package demo.generic;

public class GenericFactoryImpl<T,N> implements GenericIFactory<T,N> {
    @Override
    public T nextObject() {
        return null;
    }

    @Override
    public N nextNumber() {
        return null;
    }
}
