package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import com.feluma.faleconosco.util.jsf.FacesUtil;
import com.feluma.faleconosco.model.StatusAvaliacao;
import com.feluma.faleconosco.model.TipoAssunto;
import com.feluma.faleconosco.util.relatorio.DescricaoValor;
import com.feluma.faleconosco.service.RelatorioService;

@Named
@ViewScoped
public class AdminBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RelatorioService relatorioService;

	private PieChartModel graficoMensagemPorAssunto;
	private PieChartModel graficoMensagemPorStatus;
	private PieChartModel graficoMensagemPorSetor;
	
	public PieChartModel getGraficoMensagemPorAssunto() {
		return graficoMensagemPorAssunto;
	}

	public void setGraficoMensagemPorAssunto(PieChartModel graficoMensagemPorAssunto) {
		this.graficoMensagemPorAssunto = graficoMensagemPorAssunto;
	}

	public PieChartModel getGraficoMensagemPorStatus() {
		return graficoMensagemPorStatus;
	}

	public void setGraficoMensagemPorStatus(PieChartModel graficoMensagemPorStatus) {
		this.graficoMensagemPorStatus = graficoMensagemPorStatus;
	}
	
	public PieChartModel getGraficoMensagemPorSetor() {
		return graficoMensagemPorSetor;
	}

	public void setGraficoMensagemPorSetor(PieChartModel graficoMensagemPorSetor) {
		this.graficoMensagemPorSetor = graficoMensagemPorSetor;
	}

	@PostConstruct
	public void inicializar(){
		if(!FacesUtil.isPostback()){
			criarGraficoMensagemPorAssunto();
			criarGraficoMensagemPorStatus();
			criarGraficoMensagemPorSetor();
		}
	}

	private void criarGraficoMensagemPorAssunto() {
		graficoMensagemPorAssunto = new PieChartModel();
        
		List<DescricaoValor> valores = relatorioService.mensagemPorAssunto();
		
		for(DescricaoValor valor : valores){
			graficoMensagemPorAssunto.set(TipoAssunto.valueOf(valor.getDescricao()).getDescricao(), valor.getValor());
    	}
    	
		graficoMensagemPorAssunto.setLegendPosition("w");
		graficoMensagemPorAssunto.setShowDataLabels(true);
		graficoMensagemPorAssunto.setTitle("Mensagens por Assunto");
	}

	private void criarGraficoMensagemPorStatus() {
		graficoMensagemPorStatus = new PieChartModel();
        
		List<DescricaoValor> valores = relatorioService.mensagemPorStatus();
		
		for(DescricaoValor valor : valores){
			graficoMensagemPorStatus.set(StatusAvaliacao.valueOf(valor.getDescricao()).getDescricao(), valor.getValor());
    	}
    	
		graficoMensagemPorStatus.setLegendPosition("w");
		graficoMensagemPorStatus.setShowDataLabels(true);
		graficoMensagemPorStatus.setTitle("Mensagens por Status");
	}
	
	private void criarGraficoMensagemPorSetor() {
		graficoMensagemPorSetor = new PieChartModel();
        
		List<DescricaoValor> valores = relatorioService.mensagemPorSetor();
		
		for(DescricaoValor valor : valores){
			graficoMensagemPorSetor.set(valor.getDescricao(), valor.getValor());
    	}
    	
		graficoMensagemPorSetor.setLegendPosition("w");
		graficoMensagemPorSetor.setShowDataLabels(true);
		graficoMensagemPorSetor.setTitle("Mensagens por Setor");
	}
	
}
