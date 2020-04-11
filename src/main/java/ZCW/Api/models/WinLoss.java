package ZCW.Api.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WinLoss {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long playerId;
  private int win;
  private int lose;
  private int totalGames;
  private Double winLoss;

  public WinLoss(int win, int loss, Long playerId){
    this.win = win;
    this.lose = loss;
    this.playerId = playerId;
    totalGames = this.win + lose;
    winLoss = (double) (totalGames / this.win);
  }

  public WinLoss(){}

  public int getWin() {
    return win;
  }

  public int getLoss() {
    return lose;
  }

  public Long getPlayerId(){
    return playerId;
  }

  public int getTotalGames() {
    return totalGames;
  }

  public Double getWinLoss() {
    return winLoss;
  }

  public Long getId() {
    return id;
  }

  public void setPlayerId(Long playerId) {
    this.playerId = playerId;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setWin(int win) {
    this.win = win;
  }

  public void setLose(int lose) {
    this.lose = lose;
  }

  public void setTotalGames(int totalGames) {
    this.totalGames = totalGames;
  }

  public void setWinLoss(double winLoss) {
    this.winLoss = winLoss;
  }
}
