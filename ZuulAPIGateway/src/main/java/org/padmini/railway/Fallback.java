package org.padmini.railway;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Fallback  
{
	@RequestMapping("/userFallback")
	public String userFallback()
	{
		return "Please wait.!! It may take some time..!!!";
	}
   

}
