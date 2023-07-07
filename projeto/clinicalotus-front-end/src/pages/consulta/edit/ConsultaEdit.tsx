import React, {useContext, useEffect, useState} from 'react'
import {ConsultaAddForm, ConsultaEditForm} from "../type";
import {Button, Form} from "react-bootstrap";
import useAPI from "../../../service/api";
import {useNavigate, useParams} from "react-router-dom";

function ConsultaEdit(){
    const [state, setState] = useState<ConsultaEditForm>()
    const API = useAPI();
    const navigate = useNavigate();

    const {id} = useParams();

    useEffect(() => {
        API.get('consulta/'+id).then(data =>{
            setState((state) => ({...state, dataHora: formatDateHour(data.dataHora, 'en'), autorizacaoPlano: data.autorizacaoPlano, paciente_id: data.paciente_id, medico_id: data.medico_id} as ConsultaEditForm));
        })
    }, [])
    const handleOnChange = (e: any) => {
        setState((state) => ({...state, [e.target.name]: e.target.value} as ConsultaEditForm))
    }

    const handleOnChangeCheck = (e: any) => {
        setState((state) => ({...state, [e.target.name]: e.target.checked} as ConsultaAddForm))
    }

    const handleSubmit = (e: any) => {
        e.preventDefault();

        if(state) state.dataHora = formatDateHour(state.dataHora, 'pt');

        API.put('consulta/'+id, state).then(() => {
            navigate('/consultas');
        });
        console.log(state);
    }
    const formatDateHour = (dateHour: string | undefined, idiom: string) => {
        if (!dateHour) return '';

        if(idiom == 'pt'){
            const [date,hour] = dateHour.split('T');
            const [year, month, day] = date.split('-');
            const [hh, mm] = hour.split(':');
            return `${day}/${month}/${year} ${hh}:${mm}:00`;
        }
        else{
            const [date,hour] = dateHour.split(' ');
            const [day, month, year] = date.split('/');
            const [hh, mm] = hour.split(':');
            return `${year}-${month}-${day}T${hh}:${mm}`;
        }

    };

    return (
        <div className={'card m-auto'}>
            <div className={'card-header'}>
                <h2 className={'card-title'}>Editar Cadastro de Paciente</h2>
            </div>
            <div className={'card-body'}>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label>DATA/HORA</Form.Label>
                        <Form.Control type={'datetime-local'} name={'dataHora'} onChange={handleOnChange} value={state?.dataHora}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>AUTORIZAÇÃO PLANO?</Form.Label>
                        <Form.Check type={'checkbox'} name={'autorizacaoPlano'} onChange={handleOnChangeCheck} checked={state?.autorizacaoPlano}></Form.Check>
                    </Form.Group>
                    <Button type={'submit'}>Atualizar</Button>
                </Form>
            </div>
        </div>
    )
}
export default ConsultaEdit
