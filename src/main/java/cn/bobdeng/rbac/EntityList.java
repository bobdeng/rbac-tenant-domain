package cn.bobdeng.rbac;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface EntityList<ID, E extends Entity<ID, ?>> {
    List<E> subList(int from, int to);

    Stream<E> list();

    Optional<E> findByIdentity(ID id);
}