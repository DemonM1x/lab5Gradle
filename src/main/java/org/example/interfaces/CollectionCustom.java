package org.example.interfaces;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.TreeSet;

public interface CollectionCustom<TEntity> {
    /** validates the list of items*/
    boolean validateData();
    /** returns the TreeSet of TEntity */
    TreeSet<TEntity> get();
    /** returns the initialization time of the collection */
    ZonedDateTime getInitializationTime();
    /** returns the type of element in the collection */
    Class getElementType();
    /** saves the collection to file */
    void save();
}
