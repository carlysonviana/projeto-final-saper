import React, {useContext, useState} from 'react'
import './Login.scss'
import { LoginState } from "./types";
import { Button, Card, Form } from 'react-bootstrap';
import { useTranslation } from "react-i18next";
import useAPI from "service/api";
import Axios from "axios";
import {useNavigate} from "react-router-dom";
import {AuthContext} from "../../store/store";

function Login() {
  const [state, setState] = useState<LoginState>()
  const auth = useContext(AuthContext);
  const navigate = useNavigate();
  const API = useAPI();
  const { t} = useTranslation()
  const handleOnChange = (e: any) => {
    setState((state) => ({...state, [e.target.name]: e.target.value} as LoginState))
  }

  const handleSubmit = (e: any) => {
    e.preventDefault();

    const credentials = btoa(state?.login+':'+state?.senha);
    const config = {
        headers: {
            Authorization: 'Basic ' + credentials
        }
    }

    API.get('my/funcionario', undefined, config)
        .then((data) => {
            if(auth.setUser) auth.setUser({...data, login: state?.login, senha: state?.senha});
            navigate('/');
    }).catch( (err) => {
        console.log(err);
    })
  }

  return (
      <Card className={'col-6 m-auto'}>
        <Card.Header>
          <Card.Title>
              {t('layout.brand')}
          </Card.Title>
        </Card.Header>
        <Card.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group>
                <Form.Label>Login</Form.Label>
                <Form.Control name={'login'} value={state?.login} onChange={handleOnChange}></Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Senha</Form.Label>
                <Form.Control name={'senha'} type={'password'} value={state?.senha} onChange={handleOnChange}></Form.Control>
            </Form.Group>
            <Button type={'submit'}>Entrar</Button>
          </Form>
        </Card.Body>
      </Card>
  )
}

export default Login;
