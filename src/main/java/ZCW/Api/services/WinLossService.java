package ZCW.Api.services;

import ZCW.Api.models.*;
import ZCW.Api.repositories.*;
import kong.unirest.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.math.*;

@Service
public class WinLossService {

  private final WinLossRepo repo;

  @Autowired
  public WinLossService(WinLossRepo repos){
    repo = repos;
  }


  public WinLoss findById(Long steamAccountCode) {
    return saveToDatabaseAndReturn(steamAccountCode);
  }

  public WinLoss save(WinLoss wl){
    repo.save(wl);
    return wl;
  }

  //turns fetchedAPIQuery into the actual object
  public WinLoss makeObject(String json){
    JsonObjectMapper mapper = new JsonObjectMapper();
    WinLoss PWLObj = mapper.readValue(json, WinLoss.class);

    return PWLObj;
  }

  //used to send API request  https://api.opendota.com/api/players/48587327/wl <-- this one is for getting the win loss
  public static String fetchApiQuery(String yourQuery){
    HttpResponse<String> response = Unirest.get(yourQuery).asString();
    return response.getBody();
  }

  public WinLoss saveToDatabaseAndReturn(Long steamAccountCode){
    String jsonOfWL = fetchApiQuery("https://api.opendota.com/api/players/" + steamAccountCode +"/wl");
    WinLoss PWLObj = makeObject(jsonOfWL);
    PWLObj.setPlayerId(steamAccountCode);
    setWinLossAndPercent(PWLObj);
    return  save(PWLObj);
  }

  public void setWinLossAndPercent(WinLoss pwl){
    BigDecimal win = new BigDecimal(pwl.getWin());
    BigDecimal totalGames = new BigDecimal(pwl.getTotalGames());
    try {
      pwl.setWinLoss(win.divide(totalGames, 5, RoundingMode.CEILING).doubleValue());
    }
    catch (ArithmeticException div){
      pwl.setWinLoss(0);
    }
  }

}
