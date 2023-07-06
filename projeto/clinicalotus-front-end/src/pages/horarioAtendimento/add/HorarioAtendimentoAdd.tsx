import React, { useContext, useState } from 'react'
import { HorarioAtendimentoAddForm } from "../type";
import { Button, Form } from "react-bootstrap";
import useAPI from "../../../service/api";
import { useNavigate } from "react-router-dom";

function HorarioAtendimentoAdd() {
    const [state, setState] = useState<HorarioAtendimentoAddForm>()
    const API = useAPI();
    const navigate = useNavigate();
    const handleOnChange = (e: any) => {
        setState((state) => ({ ...state, [e.target.name]: e.target.value } as HorarioAtendimentoAddForm))
    }

    const handleSubmit = (e: any) => {
        e.preventDefault();

        if (state) state.horarioInicio = formatHour(state.horarioInicio);

        if (state) state.horarioFim = formatHour(state.horarioFim);


        API.post('horarioAtendimento', state).then(() => {
            navigate('/horarios');
        });
        console.log(state);
    }
    const formatHour = (time: string | undefined) => {
        if (!time) return '';

        const [hh, mm] = time.split(':');
        return `${hh}:${mm}:00`;
    };
    return (
        <div className={'card m-auto'}>
            <div className={'card-header'}>
                <h2 className={'card-title'}>Cadastro do Horario</h2>
            </div>
            <div className={'card-body'}>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label>Dia da Semana</Form.Label>
                        <Form.Control type={'text'} name={'diaDaSemana'} onChange={handleOnChange} value={state?.diaDaSemana}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Horario Inicial</Form.Label>
                        <Form.Control type={'time'} name={'horarioInicio'} onChange={handleOnChange} value={state?.horarioInicio}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Hora Final</Form.Label>
                        <Form.Control type={'time'} name={'horarioFim'} onChange={handleOnChange} value={state?.horarioFim}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Medico</Form.Label>
                        <Form.Control type={'number'} name={'medico_id'} onChange={handleOnChange} value={state?.medico_id}></Form.Control>
                    </Form.Group>
                    <Button type={'submit'}>Cadastrar</Button>
                </Form>
            </div>
        </div>
    )
}
export default HorarioAtendimentoAdd