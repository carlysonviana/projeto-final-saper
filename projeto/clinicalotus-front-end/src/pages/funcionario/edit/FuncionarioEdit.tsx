import React, {useContext, useEffect, useState} from 'react'
import {FuncionarioEditForm} from "../types";
import {Button, Form} from "react-bootstrap";
import useAPI from "../../../service/api";
import {useNavigate, useParams} from "react-router-dom";

function FuncionarioEdit(){
    const [state, setState] = useState<FuncionarioEditForm>()
    const API = useAPI();
    const navigate = useNavigate();

    const {id} = useParams();

    useEffect(() => {
        API.get('funcionario/'+id).then(data =>{
            setState((state) => ({...state, nome: data.nome, email: data.email, celular: data.celular, telefone: data.telefone} as FuncionarioEditForm));
        })
    }, [])
    const handleOnChange = (e: any) => {
        setState((state) => ({...state, [e.target.name]: e.target.value} as FuncionarioEditForm))
    }

    const handleSubmit = (e: any) => {
        e.preventDefault();

        // if(state) state.dataNascimento = formatDate(state.dataNascimento, 'pt');

        API.put('funcionario/'+id, state).then(() => {
            navigate('/funcionarios');
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
                <h2 className={'card-title'}>Editar Cadastro de Funcionário</h2>
            </div>
            <div className={'card-body'}>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label>Nome</Form.Label>
                        <Form.Control type={'text'} name={'nome'} onChange={handleOnChange} value={state?.nome}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>E-MAIL</Form.Label>
                        <Form.Control type={'email'} name={'email'} onChange={handleOnChange} value={state?.email}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>CELULAR</Form.Label>
                        <Form.Control type={'text'} name={'telefone'} onChange={handleOnChange} value={state?.celular}></Form.Control>
                    </Form.Group>
                    <Button type={'submit'}>Atualizar</Button>
                </Form>
            </div>
        </div>
    )
}
export default FuncionarioEdit
