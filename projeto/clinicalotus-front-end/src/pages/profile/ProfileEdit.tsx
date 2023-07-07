import React, {useContext, useEffect, useState} from 'react'
import {Profile} from "./types";
import {Button, Form} from "react-bootstrap";
import useAPI from "../../service/api";
import {useNavigate} from "react-router-dom";

function ProfileEdit(){
    const [state, setState] = useState<Profile>()
    const API = useAPI();
    const navigate = useNavigate();

    // const {id} = useParams();

    useEffect(() => {
        API.get('my').then(data =>{
            setState((state) => ({...state, nome: data.nome, email: data.email, celular: data.celular} as Profile));
        })
    }, [])
    const handleOnChange = (e: any) => {
        setState((state) => ({...state, [e.target.name]: e.target.value} as Profile))
    }

    const handleSubmit = (e: any) => {
        e.preventDefault();

        // if(state) state.dataNascimento = formatDate(state.dataNascimento, 'pt');

        API.put('my', state).then(() => {
            alert('Atualização Realizada Com Sucesso!');
            navigate('/profile');
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
                <h2 className={'card-title'}>Editar Perfil</h2>
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
export default ProfileEdit
