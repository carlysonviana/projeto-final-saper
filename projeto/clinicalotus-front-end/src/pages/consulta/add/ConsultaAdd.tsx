import React, {useContext, useState} from 'react'
import {ConsultaAddForm} from "../type";
import {Button, Form} from "react-bootstrap";
import useAPI from "../../../service/api";
import {useNavigate} from "react-router-dom";

function ConsultaAdd(){
    const [state, setState] = useState<ConsultaAddForm>()
    const API = useAPI();
    const navigate = useNavigate();
    const handleOnChange = (e: any) => {
        setState((state) => ({...state, [e.target.name]: e.target.value} as ConsultaAddForm))
    }

    const handleOnChangeCheck = (e: any) => {
        setState((state) => ({...state, [e.target.name]: e.target.checked} as ConsultaAddForm))
    }

    const handleSubmit = (e: any) => {
        e.preventDefault();

        if(state) state.dataHora = formatDateHour(state.dataHora, 'pt');

        API.post('consulta', state).then(() => {
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
                <h2 className={'card-title'}>Marcação de Consulta</h2>
            </div>
            <div className={'card-body'}>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label>Data/Hora</Form.Label>
                        <Form.Control type={'datetime-local'} name={'dataHora'} onChange={handleOnChange} value={state?.dataHora}></Form.Control>
                    </Form.Group>
                    <Form.Check className='mb-3'>
                        <Form.Check.Input name={'autorizacaoPlano'} checked={state?.autorizacaoPlano} onChange={handleOnChangeCheck}></Form.Check.Input>
                        <Form.Check.Label>Autorização Plano?</Form.Check.Label>
                    </Form.Check>
                    <Form.Group>
                        <Form.Label>Paciente</Form.Label>
                        <Form.Control type={'number'} name={'paciente_id'} onChange={handleOnChange} value={state?.paciente_id}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Médico</Form.Label>
                        <Form.Control type={'number'} name={'medico_id'} onChange={handleOnChange} value={state?.medico_id}></Form.Control>
                    </Form.Group>
                    <Button type={'submit'}>Marcar</Button>
                </Form>
            </div>
        </div>
    )
}
export default ConsultaAdd
