import React, {useContext, useState} from 'react'
import './Login.scss'
import { LoginState } from "./types";
import { Button, Card, Form } from 'react-bootstrap';
import { useTranslation } from "react-i18next";
import useAPI from "service/api";
import Axios from "axios";
import {useNavigate} from "react-router-dom";
import {AuthContext} from "../../store/store";
import {
    MDBBtn,
    MDBContainer,
    MDBRow,
    MDBCol,
    MDBInput
}
from 'mdb-react-ui-kit';

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
      <Form onSubmit={handleSubmit}>
          <MDBContainer className="my-5 gradient-form">

              <MDBRow>

                  <MDBCol col='6' className="mb-5">
                      <div className="d-flex flex-column ms-5">

                          <div className="text-center">
                              <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/lotus.webp"
                                   style={{width: '185px'}} alt="logo" />
                              <h4 className="mt-1 mb-5 pb-1">Clínica Lótus</h4>
                          </div>

                          <p>Por favor, digite seu login</p>


                          <MDBInput wrapperClass='mb-4' label='Login' name={'login'} value={state?.login} onChange={handleOnChange}/>
                          <MDBInput wrapperClass='mb-4' label='Senha' name={'senha'} type={'password'} value={state?.senha} onChange={handleOnChange}/>


                          <div className="text-center pt-1 mb-5 pb-1">
                              <Button className="mb-4 w-100 gradient-custom-2" type={'submit'}>Entrar</Button>
                              <a className="text-muted" href="#!">Esqueceu a senha?</a>
                          </div>

                          <div className="d-flex flex-row align-items-center justify-content-center pb-4 mb-4">
                              <p className={"mb-0"}>Não tem uma conta?</p>
                              <Button onClick={() => navigate('/criarConta')} type='button' className={'btn btn-danger mx-2'} >
                                  Criar Conta
                              </Button>
                          </div>

                      </div>

                  </MDBCol>

                  <MDBCol col='6' className="mb-5">
                      <div className="d-flex flex-column  justify-content-center gradient-custom-2 h-100 mb-4">

                          <div className="text-white px-3 py-4 p-md-5 mx-md-4">
                              <h4 className="mb-4">We are more than just a company</h4>
                              <p className="small mb-0">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                  tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                                  exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                              </p>
                          </div>

                      </div>

                  </MDBCol>

              </MDBRow>

          </MDBContainer>
      </Form>
  )
}

export default Login;
