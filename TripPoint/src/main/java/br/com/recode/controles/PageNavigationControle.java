package br.com.recode.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.recode.entidades.Destino;
import br.com.recode.servico.DestinoServico;
import br.com.recode.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page")
public class PageNavigationControle {
	
	private final DestinoServico destinoServico;
	
	//Paginação
	@GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }
	
	@GetMapping("/destinos")
	public ModelAndView  destinos() {
		ModelAndView modelAndView = new ModelAndView("destinos.html");
		List<Destino> destinos = destinoServico.listarTodosLista();
		modelAndView.addObject("destinos", destinos);
		return modelAndView;
    }
	
	@GetMapping("/promocoes")
    public String promocoes() {
        return "promocoes";
    }
	
	@GetMapping("/contato")
    public String contato() {
        return "contato";
    }
	
	//Carregar imagem do banco
	@GetMapping("/imagem/{id}")
    @ResponseBody
    public byte[] exibirImagen(@PathVariable("id") Long id) {
		Destino destino = this.destinoServico.buscarPorId(id);
        return destino.getImagem();
    }
}
