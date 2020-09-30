package com.example.adoederson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("produtos", MODE_PRIVATE)

        fun cadastrar() {
            if (txtProduto.text.isNotEmpty() && txtPrecoCusto.text.isNotEmpty() && txtPrecoVenda.text.isNotEmpty()) {
                sharedPreferences.edit()
                        .putString(txtProduto.text.toString() + "produto", txtProduto.text.toString())
                        .apply()
                sharedPreferences.edit().putString(txtProduto.text.toString() + "custo", txtPrecoCusto.text.toString())
                        .apply()
                sharedPreferences.edit().putString(txtProduto.text.toString() + "venda", txtPrecoVenda.text.toString())
                        .apply()

                sendMessageSucess()

                limparCampos()

            } else {
                sendMessageValidation()
            }
        }

        fun pesquisar() {
            if (txtPesquisar.text.isNotEmpty()) {
                var produto = sharedPreferences.getString(txtPesquisar.text.toString() + "produto", "")
                var custo = sharedPreferences.getString(txtPesquisar.text.toString() + "custo", "")
                var venda = sharedPreferences.getString(txtPesquisar.text.toString() + "venda", "")

                var operacao = venda!!.toFloat() - custo!!.toFloat()

                if (produto!!.isNotEmpty() && custo!!.isNotEmpty() && venda!!.isNotEmpty()) {
                    txtProduto.setText(sharedPreferences.getString(txtPesquisar.text.toString() + "produto", ""))
                    txtPrecoCusto.setText(sharedPreferences.getString(txtPesquisar.text.toString() + "custo", ""))
                    txtPrecoVenda.setText(sharedPreferences.getString(txtPesquisar.text.toString() + "venda", ""))

                    if (operacao > 0) {
                        lblLucroPrejuizo.setText("Lucro")
                        txtSoma.setText(operacao.toString())
                    } else if (operacao < 0) {
                        lblLucroPrejuizo.setText("Prejuizo")
                        txtSoma.setText(operacao.toString())
                    }
                } else {
                    Toast.makeText(this, "Produto não encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                sendMessageValidation()
            }
        }



        btnCadastrar.setOnClickListener { v: View ->
            cadastrar()
        }

        btnPesquisar.setOnClickListener { v: View ->
            pesquisar()
            disableTxtCadastrar()


        }
        btnLimpar.setOnClickListener { v: View ->
            limparCampos()
            enableTxtCadastrar()
        }

    }

    fun limparCampos() {
        txtProduto.text.clear()
        txtPrecoCusto.text.clear()
        txtPrecoVenda.text.clear()
        txtSoma.text.clear()
        txtPesquisar.text.clear()
    }
    fun sendMessageSucess(){
        Toast.makeText(this, "Produto Cadastrado com sucesso", Toast.LENGTH_SHORT).show()
    }
    fun sendMessageValidation(){
        Toast.makeText(this, "Favor preencha os campos", Toast.LENGTH_SHORT).show()
    }
    fun disableTxtCadastrar(){
        btnCadastrar.isEnabled = false;
    }
    fun enableTxtCadastrar(){
        btnCadastrar.isEnabled = true;
    }


}