package com.sacra.ecommerce.web.controller;

public interface ViewsPaths {
	/**
	 * @deprecated Veremos porque no debe estar, ni tan siquiera en una constante.  
	 */
	public static final String ROOT_CONTEXT = "/SacraWeb";
	
	public static final String SIGN_IN = "/html/signin/signin.jsp";	
	public static final String INDEX = ROOT_CONTEXT+"/html/index.jsp";
	
	public static final String TRANSPORTISTA_SEARCH = "/html/shipper/shipper-search.jsp";
	public static final String TRANSPORTISTA_DETAIL = "/html/shipper/shipper-detail.jsp";
		
} 
