package br.com.recode.controles;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.recode.entidades.Destino;
import br.com.recode.servico.DestinoServico;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminNavigationControle {
	
	private final DestinoServico destinoServico;
	
	@GetMapping({"/", "/admin/","/admin",})
    public String redirect() {
        return "redirect:/admin/listar_destinos";
    }
	
	@GetMapping({"/listar_destinos"})
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("admin/listarDestinos.html");
		List<Destino> destinos = destinoServico.listarTodosLista();
		modelAndView.addObject("destinos", destinos);
		return modelAndView;
	}
	
	//chama a view Cadastrar e passa um objeto vazio
		@GetMapping("/cadastro")
	    public ModelAndView cadastrar() {
	       ModelAndView modelAndView = new ModelAndView("admin/cadastroDestino");
	       modelAndView.addObject("destino", new Destino());
	       return modelAndView;
		}
	
	@PostMapping("/cadastrar")
    public ModelAndView cadastrar(Destino destino, @RequestParam("imagemDestino") MultipartFile file) throws IOException {
       try {
    	   destino.setImagem(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }    
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/");
        destinoServico.cadastrarDestino(destino);
       return modelAndView;
    }
	
	@GetMapping("/destino/{id}")
    public ModelAndView detalhar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/detalhar.html");
       Destino destino = destinoServico.buscarPorId(id);
        modelAndView.addObject("destino", destino);
       return modelAndView;
	}
	@PostMapping("/alterar")
    public ModelAndView editar(Destino destino, @RequestParam("fileCliente") MultipartFile file) throws IOException {
		try {
			destino.setImagem(file.getBytes());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/");
        destinoServico.atualizarDestino(destino);
        return modelAndView;
    }
	
	@GetMapping("/{id}/excluir")
    public ModelAndView excluir(@PathVariable Long id) {
       ModelAndView modelAndView = new ModelAndView("redirect:/admin/");
       destinoServico.deletarDestino(id);
       return modelAndView;
    }
}
