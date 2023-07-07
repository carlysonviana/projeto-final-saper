import React, {useContext, useState} from 'react'
import {PacienteAddForm} from "../type";
import {Button, Form} from "react-bootstrap";
import useAPI from "../../../service/api";
import {useNavigate} from "react-router-dom";

function PacienteAdd(){
    const [state, setState] = useState<PacienteAddForm>()
    const API = useAPI();
    const navigate = useNavigate();
    const handleOnChange = (e: any) => {
        setState((state) => ({...state, [e.target.name]: e.target.value} as PacienteAddForm))
    }

    const handleSubmit = (e: any) => {
        e.preventDefault();

        if(state) state.dataNascimento = formatDate(state.dataNascimento);

        API.post('paciente', state).then(() => {
            navigate('/pacientes');
        });
        console.log(state);
    }
    const formatDate = (date: string | undefined) => {
        if (!date) return '';

        const [year, month, day] = date.split('-');
        return `${day}/${month}/${year}`;
    };
    return (
        <div className={'card m-auto'}>
            <div className={'card-header'}>
                <h2 className={'card-title'}>Cadastro de Paciente</h2>
            </div>
            <div className={'card-body'}>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label>Nome</Form.Label>
                        <Form.Control type={'text'} name={'nome'} onChange={handleOnChange} value={state?.nome}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>CPF</Form.Label>
                        <Form.Control type={'text'} name={'cpf'} onChange={handleOnChange} value={state?.cpf}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>E-mail</Form.Label>
                        <Form.Control type={'email'} name={'email'} onChange={handleOnChange} value={state?.email}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Data de Nascimento</Form.Label>
                        <Form.Control type={'date'} name={'dataNascimento'} onChange={handleOnChange} value={state?.dataNascimento}></Form.Control>
                    </Form.Group>
                    <Button type={'submit'}>Cadastrar</Button>
                </Form>
            </div>
        </div>
    )
}
export default PacienteAdd
