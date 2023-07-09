import React, {useContext, useState} from 'react'
import './Login.module.scss'
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
import styles from "./Login.module.scss";
import logoInicial from "../../assets/imgs/logo-inicial.png";

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
      <Form className={styles.loginStyle} onSubmit={handleSubmit}>
          <MDBContainer className="my-5 gradient-form">

              <MDBRow>

                  <MDBCol col='6' className="mb-5">
                      <div className="d-flex flex-column ms-5">

                          <div className="text-center">
                              <img className={styles.space} src={logoInicial} alt={'Logo'}/>
                          </div>

                          <p>Por favor, digite seu login e senha</p>


                          <MDBInput wrapperClass='mb-4' placeholder={'Login'} name={'login'} value={state?.login} onChange={handleOnChange}/>
                          <MDBInput wrapperClass='mb-4' placeholder={'Senha'} name={'senha'} type={'password'} value={state?.senha} onChange={handleOnChange}/>


                          <div className="text-center pt-1 mb-5 pb-1">
                              <Button className="mb-4 w-100 gradient-custom-2" type={'submit'}>Entrar</Button>
                          </div>

                      </div>

                  </MDBCol>

                  {/*<MDBCol col='6' className="mb-5">*/}
                  {/*    <div className="d-flex flex-column  justify-content-center gradient-custom-2 h-100 mb-4">*/}

                  {/*        <div className="text-white px-3 py-4 p-md-5 mx-md-4">*/}
                  {/*            <h4 className="mb-4">We are more than just a company</h4>*/}
                  {/*            <p className="small mb-0">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod*/}
                  {/*                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud*/}
                  {/*                exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.*/}
                  {/*            </p>*/}
                  {/*        </div>*/}

                  {/*    </div>*/}

                  {/*</MDBCol>*/}

              </MDBRow>

          </MDBContainer>
      </Form>
  )
}

export default Login;
