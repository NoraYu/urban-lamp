package xiyu.one2manywithcheckbox;

import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;



@Controller
public class HomeController {
@Autowired
PersonRepository personRepository;

@Autowired
SkillRepository skillRepository;
    @GetMapping("/")
    public String skillforms(Model model){
        Person nora=new Person();


       // personRepository.save(nora);


        String[] checkItems= {"Java", "AWS", "Spring"};

        model.addAttribute("checkItems",checkItems);
        model.addAttribute("nora",nora);

        return "skillform";
    }

    @PostMapping("/pocesssperson")
    public String skill(@ModelAttribute Person nora, @RequestParam String[] checkItems, Model model){
        String skills=Arrays.toString(checkItems);
        System.out.println(skills);
        System.out.println(nora.getName());


        for(String s:checkItems){
            System.out.println("skill name: "+ s);
            Skill nora_skill=new Skill(s);
            System.out.println("new skill generated");
            nora_skill.setPerson(nora);
            System.out.println("技能持有人关系建立为： "+ nora_skill.getPerson().getName());

            //nora.addSkills(nora_skill);
            personRepository.save(nora);
            skillRepository.save(nora_skill);
            System.out.println("save to db");

        }
        Person xiyu=personRepository.findById(1l).get();
        System.out.println("姓名： "+xiyu.getName());

        System.out.println("技能： "+xiyu.getSkills());

        //model.addAttribute("nora",xiyu);
        //return "person";
        return "redirect:/nora";

    }

    @RequestMapping("/nora")
    public String nora(Model model,@ModelAttribute Person nora){

        model.addAttribute("nora",personRepository.findById(1l).get());
        System.out.println("技能： "+personRepository.findById(1l).get().getSkills());
        //model.addAttribute("nora",nora);
        //System.out.println("技能： "+nora.getSkills());
        return "list";}





}
