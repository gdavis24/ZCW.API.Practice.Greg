package ZCW.Api.controllers;


import ZCW.Api.services.WinLossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WinLossController {

    private static WinLossService serv;

    @Autowired
    public WinLossController(WinLossService ser) { serv = ser; }

    @GetMapping("/winloss/{account_id}")
    public static ResponseEntity<?> getWinLoss(@PathVariable long account_id) {
      return new ResponseEntity<>(serv.findById(account_id), HttpStatus.CREATED);
    }


}
