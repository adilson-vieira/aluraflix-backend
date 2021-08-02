package com.aluraflix.aluraflix;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class VideoTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void buscarVideoPeloTituloERetornarStatusCode404() throws Exception {
		URI uri = new URI("/videos/?search=1111110111101011101001100110");
		mockMvc.perform(MockMvcRequestBuilders.get(uri).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}

	@Test
	public void buscarVideoPeloTituloERetornarStatusCode200() throws Exception {
		URI uri = new URI("/videos/?search=título");
		mockMvc.perform(MockMvcRequestBuilders.get(uri).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void salvarNovoVideoComCategoriaERetornarStatusCode201() throws Exception {

		String json = "{\"titulo\":\"novo título do vídeo\", " + "\"descricao\":\"nova descrição do vídeo\", "
				+ "\"url\":\"https://www.youtube.com/watch?v=code_here\",\"categoriaId\": \"1\" }";

		URI uri = new URI("/videos");

		mockMvc.perform(MockMvcRequestBuilders.post(uri).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void alterarVideoERetornarStatusCode200() throws Exception {

		String json = "{\"titulo\":\"novo título do vídeo\", " + "\"descricao\":\"nova descrição do vídeo\", "
				+ "\"url\":\"https://www.youtube.com/watch?v=code_here\" }";

		URI uri = new URI("/videos/1");

		mockMvc.perform(MockMvcRequestBuilders.put(uri).content(json).header(HttpHeaders.CONTENT_TYPE,
				MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void excluirVideoERetornarStatusCode200() throws Exception {
		URI uri = new URI("/videos/5");
		mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void salvarNovoVideoSemCategoriaERetornarStatusCode201() throws Exception {
		String json = "{\"titulo\":\"novo título do vídeo\", " + "\"descricao\":\"nova descrição do vídeo\", "
				+ "\"url\":\"https://www.youtube.com/watch?v=code_here\"}";

		mockMvc.perform(post("/videos").content(json).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	public void listarTodosOsVideosERetornaStatusCode200() throws Exception {
		URI uri = new URI("/videos");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void buscarUmVideoEspecificoERetornarStatusCode200() throws Exception {
		URI uri = new URI("/videos/1");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void buscarUmVideoEspecificoERetornarStatusCode404() throws Exception {
		URI uri = new URI("/videos/1000");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(404));
	}
}
