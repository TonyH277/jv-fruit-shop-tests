package core.basesyntax.service.strategy.impl;

import core.basesyntax.model.Transaction;
import core.basesyntax.repository.StorageRepository;
import core.basesyntax.service.strategy.OperationStrategy;

public class ReturnOperation implements OperationStrategy {
    private static final String NEGATIVE_VALUE_MESSAGE = "Unsupported value for RETURN operation: ";
    private StorageRepository repository;

    public ReturnOperation(StorageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Transaction transaction) {
        Integer actualAmount = repository.getProducts().get(transaction.getProduct());
        Integer newAmount = actualAmount + transaction.getValue();
        if (newAmount < 0) {
            throw new RuntimeException(NEGATIVE_VALUE_MESSAGE + newAmount);
        }
        transaction.setValue(newAmount);
        repository.add(transaction);
    }
}
