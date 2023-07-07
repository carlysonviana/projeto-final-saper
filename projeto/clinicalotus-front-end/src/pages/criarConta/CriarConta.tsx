import React, {useContext, useState} from 'react'
import './CriarConta.scss'
import {Conta} from "./types";
import { Button, Card, Form } from 'react-bootstrap';
import { useTranslation } from "react-i18next";
import useAPI from "service/api";
import Axios from "axios";
import {useNavigate} from "react-router-dom";
import {AuthContext} from "../../store/store";

function CriarConta() {
  const [state, setState] = useState<Conta>()
  const auth = useContext(AuthContext);
  const navigate = useNavigate();
  const API = useAPI();
  const { t} = useTranslation()
  const handleOnChange = (e: any) => {
    setState((state) => ({...state, [e.target.name]: e.target.value} as Conta))
  }

  const handleSubmit = (e: any) => {
    e.preventDefault();

    API.post('funcionario', state).then(() => {
        alert("Cadastro Criado Com Sucesso!");
        navigate('/login');
    });

  }

  return (
      <Card className={'col-6 m-auto'}>
        <Card.Header>
          <Card.Title>
              Criação de Conta
          </Card.Title>
        </Card.Header>
        <Card.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group>
                <Form.Label>NOME</Form.Label>
                <Form.Control name={'nome'} value={state?.nome} onChange={handleOnChange}></Form.Control>
            </Form.Group>
            <Form.Group>
              <Form.Label>CPF</Form.Label>
              <Form.Control name={'cpf'} value={state?.cpf} onChange={handleOnChange}></Form.Control>
            </Form.Group>
            <Form.Group>
              <Form.Label>LOGIN</Form.Label>
              <Form.Control name={'login'} value={state?.login} onChange={handleOnChange}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>SENHA</Form.Label>
                <Form.Control name={'senha'} type={'password'} value={state?.senha} onChange={handleOnChange}></Form.Control>
            </Form.Group>
            <Form.Group>
               <Form.Label>CELULAR</Form.Label>
               <Form.Control name={'celular'} value={state?.celular} onChange={handleOnChange}></Form.Control>
            </Form.Group>
              <Form.Group>
                  <Form.Label>CATEGORIA DO(A) FUNCIONÁRIO(A)</Form.Label>
                  <Form.Control as="select" name="categoriaFuncionario_id" value={state?.categoriaFuncionario_id} onChange={handleOnChange}>
                      <option value={2}>1 - MÉDICO(A)</option>
                      <option value={3}>2 - RECEPCIONISTA</option>
                  </Form.Control>
              </Form.Group>
            <Button type={'submit'}>Criar Conta</Button>
          </Form>
        </Card.Body>
      </Card>
  )
}

export default CriarConta;
