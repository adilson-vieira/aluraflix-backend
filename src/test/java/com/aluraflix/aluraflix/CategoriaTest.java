package com.aluraflix.aluraflix;

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
public class CategoriaTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void buscarVideosPorCategoriaERetornarStatusCode200() throws Exception {
		URI uri = new URI("/categorias/1/videos");
		mockMvc.perform(MockMvcRequestBuilders.get(uri).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void salvarNovaCategoriaERetornarStatusCode201() throws Exception {
		String json = "{\"titulo\":\"TESTE DE CATEGORIA\", \"cor\":\"#FFCCAA\"}";
		URI uri = new URI("/categorias");
		mockMvc.perform(MockMvcRequestBuilders.post(uri).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.content(json)).andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void alterarCategoriaERetornarStatusCode200() throws Exception {
		String json = "{\"titulo\":\"TESTE DE CATEGORIA\", \"cor\":\"#FFCCAA\"}";
		URI uri = new URI("/categorias/1");
		mockMvc.perform(MockMvcRequestBuilders.put(uri).content(json).header(HttpHeaders.CONTENT_TYPE,
				MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void excluirCategoriaERetornarStatusCode200() throws Exception {
		URI uri = new URI("/categorias/11");
		mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void listarTodasAsCategoriasERetornarStatusCode200() throws Exception {
		URI uri = new URI("/categorias");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void buscarUmaCategoriaERetornarStatusCode200() throws Exception {
		URI uri = new URI("/categorias/1");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void buscarUmaCategoriaERetornarStatusCode404() throws Exception {
		URI uri = new URI("/categorias/1000");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(404));
	}

}
