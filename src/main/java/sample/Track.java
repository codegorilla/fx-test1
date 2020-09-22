package sample;

public class Track {

  int id;
  double course;
  double speed;

  public Track (int id, double course, double speed) {
    this.id = id;
    this.course = course;
    this.speed = speed;
  }

  public int getId () {
    return id;
  }

  public double getCourse () {
    return course;
  }

  public double getSpeed () {
    return speed;
  }

}
