package pl.camp.it.rent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.rent.services.ICartService;
import pl.camp.it.rent.session.SessionObject;

import javax.annotation.Resource;

@Controller
@RequestMapping(value="/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value="/add/{tale}",method = RequestMethod.GET)
    public String addToCatr(@PathVariable String tale){
        if(!this.sessionObject.isLogged()){
            return "redirect:/main";
        }
        this.cartService.addToCart(tale);
        return "redirect:/main";

    }
    @RequestMapping(value="",method = RequestMethod.GET)
    public String cart(Model model){
        if(!this.sessionObject.isLogged()){
            return "redirect:/main";
        }
        model.addAttribute("logged",this.sessionObject.isLogged());
        model.addAttribute("cart",this.sessionObject.getCart());
        model.addAttribute("sum",this.sessionObject.getCart().clculateSum());
       return "cart";
    }
}
