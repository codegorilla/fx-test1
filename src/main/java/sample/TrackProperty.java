package sample;

public class TrackProperty {

  // For now assume all values are integers. They might need to be objects.
  String name;
  int value;

  public TrackProperty (String name, int value) {
    this.name = name;
    this.value = value;
  }

  public String getName () {
    return name;
  }

  public int getValue () {
    return value;
  }

}
