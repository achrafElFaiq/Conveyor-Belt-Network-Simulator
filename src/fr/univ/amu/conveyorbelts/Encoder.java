package fr.univ.amu.conveyorbelts;

@FunctionalInterface
public interface Encoder<T> {
  String encode(T value);
}
