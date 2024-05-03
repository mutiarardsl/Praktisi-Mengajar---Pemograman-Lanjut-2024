public class generic<G>{
    private G data;

  public void add(G data) {
    this.data = data;
  }

  public G get() {
    return data;
}
}