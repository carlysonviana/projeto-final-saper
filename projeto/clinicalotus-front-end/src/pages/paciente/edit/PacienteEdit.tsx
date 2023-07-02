import React, {useContext, useEffect, useState} from 'react'
import {PacienteEditForm} from "../type";
import {Button, Form} from "react-bootstrap";
import useAPI from "../../../service/api";
import {useNavigate, useParams} from "react-router-dom";

function PacienteEdit(){
    const [state, setState] = useState<PacienteEditForm>()
    const API = useAPI();
    const navigate = useNavigate();

    const {id} = useParams();

    useEffect(() => {
        API.get('paciente/'+id).then(data =>{
            setState((state) => ({...state, nome: data.nome, cpf: data.cpf, email: data.email, dataNascimento: formatDate(data.dataNascimento, 'en')} as PacienteEditForm));
        })
    }, [])
    const handleOnChange = (e: any) => {
        setState((state) => ({...state, [e.target.name]: e.target.value} as PacienteEditForm))
    }

    const handleSubmit = (e: any) => {
        e.preventDefault();

        if(state) state.dataNascimento = formatDate(state.dataNascimento, 'pt');

        API.put('paciente/'+id, state).then(() => {
            navigate('/pacientes');
        });
        console.log(state);
    }
    const formatDate = (date: string | undefined, idiom: string) => {
        let formattedDate = '';
        if (!date) return '';

        if(idiom == 'pt'){
            const [year, month, day] = date.split('-');
            formattedDate = `${day}/${month}/${year}`;
        }
        else{
            const [day, month, year] = date.split('/');
            formattedDate = `${year}-${month}-${day}`;
        }
        return formattedDate;
    };

    return (
        <div className={'card m-auto'}>
            <div className={'card-header'}>
                <h2 className={'card-title'}>Editar Cadastro de Paciente</h2>
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
                    <Button type={'submit'}>Atualizar</Button>
                </Form>
            </div>
        </div>
    )
}
export default PacienteEdit
