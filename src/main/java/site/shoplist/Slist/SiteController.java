package site.shoplist.Slist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.shoplist.Slist.models.Hoz;
import site.shoplist.Slist.models.Pharmacy;
import site.shoplist.Slist.models.ShoppingList;
import site.shoplist.Slist.repositories.HozRepository;
import site.shoplist.Slist.repositories.PharmRepository;
import site.shoplist.Slist.repositories.ShopListRepository;

import java.util.*;

@Controller
public class SiteController {

    @Autowired
    private HozRepository hozRepository;

    @Autowired
    private ShopListRepository shopListRepository;

    @Autowired
    private PharmRepository pharmRepository;

    @GetMapping("/")
    public String getMain(Model model, @RequestParam(required = false) String tabId) {
        Iterable<Hoz> hozs = hozRepository.findAll();
        Iterable<ShoppingList> goods = shopListRepository.findAll();
        Iterable<Pharmacy> pharmacy = pharmRepository.findAll();

        // Сортируем данные
        List<Hoz> sortedHozs = new ArrayList<>();
        hozs.forEach(sortedHozs::add);
        Collections.sort(sortedHozs, new HozComparator());

        List<ShoppingList> sortedGoods = new ArrayList<>();
        goods.forEach(sortedGoods::add);
        Collections.sort(sortedGoods, new ShoppingListComparator());

        List<Pharmacy> sortedPharmacy = new ArrayList<>();
        pharmacy.forEach(sortedPharmacy::add);
        Collections.sort(sortedPharmacy, new PharmacyComparator());

        model.addAttribute("hGoods", sortedHozs);
        model.addAttribute("goods", sortedGoods);
        model.addAttribute("pharmacy", sortedPharmacy);
        model.addAttribute("tabId", tabId);
        return "main";
    }

    @RequestMapping("/")
    public String getMain(Model model, @RequestParam(required = false) Integer id, @RequestParam(required = false) String name,  @RequestParam(required = false) String change, @RequestParam(required = false) String formId, @RequestParam(required = false) String postType, @RequestParam(required = false) Integer del, @RequestParam(required = false) String tabId, @RequestParam(required = false) String submit) {

        if ("Изменить".equals(change)) {
            switch (formId) {
                case "1":
                    ShoppingList shoppinglistElement = shopListRepository.findById(id).orElseThrow();
                    shoppinglistElement.setName(name);
                    shopListRepository.save(shoppinglistElement);
                    break;
                case "2":
                    Hoz hozElement = hozRepository.findById(id).orElseThrow();
                    hozElement.setName(name);
                    hozRepository.save(hozElement);
                    break;
                case "3":
                    Pharmacy pharmacyElement = pharmRepository.findById(id).orElseThrow();
                    pharmacyElement.setName(name);
                    pharmRepository.save(pharmacyElement);
                    break;
            }
        }

        if ("yes".equals(postType)) {
            switch (formId) {
                case "1":
                    ShoppingList shoppinglistElementYes = shopListRepository.findById(id).orElseThrow();
                    shoppinglistElementYes.setStatus(1);
                    shopListRepository.save(shoppinglistElementYes);
                    break;
                case "2":
                    Hoz hozElementYes = hozRepository.findById(id).orElseThrow();
                    hozElementYes.setStatus(1);
                    hozRepository.save(hozElementYes);
                    break;
                case "3":
                    Pharmacy pharmacyElementYes = pharmRepository.findById(id).orElseThrow();
                    pharmacyElementYes.setStatus(1);
                    pharmRepository.save(pharmacyElementYes);
                    break;
            }
        }

        if ("change".equals(postType)) {
            switch (formId) {
                case "1":
                    ShoppingList shoppinglistElementChange = shopListRepository.findById(id).orElseThrow();
                    shoppinglistElementChange.setStatus(0);
                    shopListRepository.save(shoppinglistElementChange);
                    break;
                case "2":
                    Hoz hozElementChange = hozRepository.findById(id).orElseThrow();
                    hozElementChange.setStatus(0);
                    hozRepository.save(hozElementChange);
                    break;
                case "3":
                    Pharmacy pharmacyElementChange = pharmRepository.findById(id).orElseThrow();
                    pharmacyElementChange.setStatus(0);
                    pharmRepository.save(pharmacyElementChange);
                    break;
            }
        }

        if (del != null && del == 1) {
            switch ((formId != null) ? formId : ((tabId != null) ? tabId : "1")) {
                case "1":
                    ShoppingList shoppinglistElementForDel = shopListRepository.findById(id).orElseThrow();
                    shopListRepository.delete(shoppinglistElementForDel);
                    break;
                case "2":
                    Hoz hozElementForDel = hozRepository.findById(id).orElseThrow();
                    hozRepository.delete(hozElementForDel);
                    break;
                case "3":
                    Pharmacy pharmacyElementForDel = pharmRepository.findById(id).orElseThrow();
                    pharmRepository.delete(pharmacyElementForDel);
                    break;
            }
        }

        if ("addNewElement".equals(submit)) {
            switch (formId) {
                case "1":
                    ShoppingList newShoppinglistElement = new ShoppingList();
                    newShoppinglistElement.setName(name);
                    newShoppinglistElement.setStatus(0);
                    newShoppinglistElement.setCount(1);
                    shopListRepository.save(newShoppinglistElement);
                    break;
                case "2":
                    Hoz newHozElement = new Hoz();
                    newHozElement.setName(name);
                    newHozElement.setStatus(0);
                    newHozElement.setCount(1);
                    hozRepository.save(newHozElement);
                    break;
                case "3":
                    Pharmacy newPharmacyElement = new Pharmacy();
                    newPharmacyElement.setName(name);
                    newPharmacyElement.setStatus(0);
                    newPharmacyElement.setCount(1);
                    pharmRepository.save(newPharmacyElement);
                    break;
            }
        }

        Iterable<Hoz> hozs = hozRepository.findAll();
        Iterable<ShoppingList> goods = shopListRepository.findAll();
        Iterable<Pharmacy> pharmacy = pharmRepository.findAll();

        // Сортируем данные
        List<Hoz> sortedHozs = new ArrayList<>();
        hozs.forEach(sortedHozs::add);
        Collections.sort(sortedHozs, new HozComparator());

        List<ShoppingList> sortedGoods = new ArrayList<>();
        goods.forEach(sortedGoods::add);
        Collections.sort(sortedGoods, new ShoppingListComparator());

        List<Pharmacy> sortedPharmacy = new ArrayList<>();
        pharmacy.forEach(sortedPharmacy::add);
        Collections.sort(sortedPharmacy, new PharmacyComparator());

        model.addAttribute("hGoods", sortedHozs);
        model.addAttribute("goods", sortedGoods);
        model.addAttribute("pharmacy", sortedPharmacy);
        model.addAttribute("tabId", formId);
        return "main";
    }


    // Классы Comparators внутри файла контроллера
    static class HozComparator implements Comparator<Hoz> {
        @Override
        public int compare(Hoz s1, Hoz s2) {
            // Сначала сравниваем по статусу (0 считается "меньше" чем 1)
            int statusComparison = Integer.compare(s1.getStatus(), s2.getStatus());

            if (statusComparison != 0) {
                return statusComparison;
            }

            // Если статусы одинаковы, сравниваем по имени
            return s1.getName().compareTo(s2.getName());
        }
    }

    static class ShoppingListComparator implements Comparator<ShoppingList> {
        @Override
        public int compare(ShoppingList s1, ShoppingList s2) {
            // Сначала сравниваем по статусу (0 считается "меньше" чем 1)
            int statusComparison = Integer.compare(s1.getStatus(), s2.getStatus());

            if (statusComparison != 0) {
                return statusComparison;
            }

            // Если статусы одинаковы, сравниваем по имени
            return s1.getName().compareTo(s2.getName());
        }
    }

    static class PharmacyComparator implements Comparator<Pharmacy> {
        @Override
        public int compare(Pharmacy s1, Pharmacy s2) {
            // Сначала сравниваем по статусу (0 считается "меньше" чем 1)
            int statusComparison = Integer.compare(s1.getStatus(), s2.getStatus());

            if (statusComparison != 0) {
                return statusComparison;
            }

            // Если статусы одинаковы, сравниваем по имени
            return s1.getName().compareTo(s2.getName());
        }
    }
}
