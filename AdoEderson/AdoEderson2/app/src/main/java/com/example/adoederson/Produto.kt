package com.example.adoederson

class Produto {

    var nome: String
    var venda: Double
    var custo: Double


    constructor(nome: String, custo: Double, venda: Double){
        this.nome = nome
        this.custo = custo
        this.venda = venda
    }

}