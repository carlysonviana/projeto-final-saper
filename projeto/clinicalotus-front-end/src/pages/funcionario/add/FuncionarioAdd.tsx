import React, {useContext, useState} from 'react'
import {FuncionarioAddForm, MedicoAddForm} from "../types";
import {Button, Card, Form, Row} from 'react-bootstrap';
import { useTranslation } from "react-i18next";
import useAPI from "service/api";
import Axios from "axios";
import {useNavigate} from "react-router-dom";
import {AuthContext} from "../../../store/store";

function FuncionarioAdd() {
    const [state, setState] = useState<FuncionarioAddForm>()
    const auth = useContext(AuthContext);
    const navigate = useNavigate();
    const API = useAPI();
    const { t} = useTranslation()
    const handleOnChange = (e: any) => {
        setState((state) => ({...state, [e.target.name]: e.target.value} as FuncionarioAddForm))
    }

    const handleSubmit = (e: any) => {
        e.preventDefault();

        API.post('funcionario', state).then(() => {
            alert("Cadastro Criado Com Sucesso!");
            navigate('/funcionarios');
        });

    }

    return (
        <Card className={'col-6 m-auto'}>
            <Card.Header>
                <Card.Title>
                    Cadastro de Funcionário
                </Card.Title>
            </Card.Header>
            <Card.Body>
                <Form onSubmit={handleSubmit}>
                    <Row className="row-cols-2">
                        <Form.Group>
                            <Form.Label>NOME</Form.Label>
                            <Form.Control name={'nome'} value={state?.nome} onChange={handleOnChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>E-MAIL</Form.Label>
                            <Form.Control name={'email'} value={state?.email} onChange={handleOnChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>CPF</Form.Label>
                            <Form.Control name={'cpf'} value={state?.cpf} onChange={handleOnChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>LOGIN</Form.Label>
                            <Form.Control name={'login'} value={state?.login} onChange={handleOnChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>SENHA</Form.Label>
                            <Form.Control name={'senha'} type={'password'} value={state?.senha} onChange={handleOnChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>CELULAR</Form.Label>
                            <Form.Control name={'celular'} value={state?.celular} onChange={handleOnChange} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>CATEGORIA DO(A) FUNCIONÁRIO(A)</Form.Label>
                            <Form.Control as="select" name="categoriaFuncionario_id" value={state?.categoriaFuncionario_id} onChange={handleOnChange}>
                                <option value={1}>ADMINISTRADOR(A)</option>
                                <option value={2}>MÉDICO(A)</option>
                                <option value={3}>RECEPCIONISTA</option>
                            </Form.Control>
                        </Form.Group>
                    </Row>
                    <Button type={'submit'}>Cadastrar Funcionário</Button>
                </Form>
            </Card.Body>
        </Card>
    )
}

export default FuncionarioAdd;
