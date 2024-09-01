package fr.univ.amu.conveyorbelts.tools;


import java.math.BigInteger;
import java.util.Objects;

public class Ratio implements Comparable<Ratio> {
  
  public final BigInteger numerator;
  public final BigInteger denominator;

  public static final Ratio ZERO = Ratio.of(0,1);
  public static final Ratio ONE = Ratio.of(1,1);

  private Ratio(BigInteger n, BigInteger d) {
    this.numerator = n;
    this.denominator = d;
  }

  public static Ratio of(BigInteger n, BigInteger d) {
    boolean isPositive = n.compareTo(BigInteger.ZERO) * d.compareTo(BigInteger.ZERO) >= 0;
    n = n.abs();
    d = d.abs();
    BigInteger gcd = gcd(n,d);
    return new Ratio(
        isPositive ? n.divide(gcd) : n.divide(gcd).negate(),
        d.divide(gcd)
    );
  }

  public static Ratio of(long n, long d) {
    return of(BigInteger.valueOf(n), BigInteger.valueOf(d));
  }

  private static BigInteger gcd(BigInteger a, BigInteger b) {
    while (!b.equals(BigInteger.ZERO)) {
      BigInteger r = a.remainder(b);
      a = b;
      b = r;
    }
    return a;
  }


  public Ratio add(Ratio r) {
    return Ratio.of(
        this.numerator.multiply(r.denominator).add(this.denominator.multiply(r.numerator)),
        this.denominator.multiply(r.denominator)
    );
  }

  public Ratio multipy(Ratio r) {
    return Ratio.of(
        this.numerator.multiply(r.numerator),
        this.denominator.multiply(r.denominator)
    );
  }

  public Ratio negate() {
    return new Ratio(this.numerator.negate(), this.denominator);
  }

  public Ratio subtract(Ratio r) {
    return this.add(r.negate());
  }

  public Ratio divide(Ratio r) {
    return Ratio.of(
        this.numerator.multiply(r.denominator),
        this.denominator.multiply(r.numerator)
    );
  }

  public boolean isStrictlyPositive() {
    return this.numerator.compareTo(BigInteger.ZERO) > 0;
  }

  public boolean isZero() {
    return this.numerator.equals(BigInteger.ZERO);
  }

  public boolean isStrictlyNegative() {
    return this.numerator.compareTo(BigInteger.ZERO) < 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ratio ratio = (Ratio) o;
    return this.numerator.equals(ratio.numerator) &&
        this.denominator.equals(ratio.denominator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, denominator);
  }

  public int compareTo(Ratio r) {
    return this.subtract(r).numerator.compareTo(BigInteger.ZERO);
  }

}
