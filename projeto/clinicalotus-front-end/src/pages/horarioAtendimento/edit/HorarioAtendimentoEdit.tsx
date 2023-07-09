import React, { useContext, useEffect, useState } from 'react'
import { HorarioAtendimentoEditForm } from "../type";
import { Button, Form } from "react-bootstrap";
import useAPI from "../../../service/api";
import { useNavigate, useParams } from "react-router-dom";

function HorarioAtendimentoEdit() {
    const [state, setState] = useState<HorarioAtendimentoEditForm>()
    const API = useAPI();
    const navigate = useNavigate();

    const { id } = useParams();

    useEffect(() => {
        API.get('horarioAtendimento/' + id).then(data => {
            console.log(data);
            setState((state) => ({ ...state, diaDaSemana: data.diaDaSemana, horarioInicio: formatHour(data.horarioInicio), horarioFim: formatHour(data.horarioInicio), medico_id: data.medico_id } as HorarioAtendimentoEditForm));
        })
    }, [])
    const handleOnChange = (e: any) => {
        setState((state) => ({ ...state, [e.target.name]: e.target.value } as HorarioAtendimentoEditForm))
    }

    const handleSubmit = (e: any) => {
        e.preventDefault();

        if (state) state.horarioInicio = formatHour(state.horarioInicio);

        if (state) state.horarioFim = formatHour(state.horarioFim);

        API.put('horarioAtendimento/' + id, state).then(() => {
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
                <h2 className={'card-title'}>Editar Cadastro de Horario</h2>
            </div>
            <div className={'card-body'}>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label>Dia da Semana</Form.Label>
                        <Form.Control as={'select'} name={'diaDaSemana'} onChange={handleOnChange} value={state?.diaDaSemana}>
                            <option selected>Open this select menu</option>
                            <option value={"segunda-feira"}>SEGUNDA-FEIRA</option>
                            <option value={"terça-feira"}>TERÇA-FEIRA</option>
                            <option value={"quarta-feira"}>QUARTA-FEIRA</option>
                            <option value={"quinta-feira"}>QUINTA-FEIRA</option>
                            <option value={"sexta-feira"}>SEXTA-FEIRA</option>
                            <option value={"sábado"}>SÁBADO</option>
                            <option value={"domingo"}>DOMINGO</option>
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Horario Inicial</Form.Label>
                        <Form.Control type={'time'} name={'horarioInicio'} onChange={handleOnChange} value={state?.horarioInicio}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Horario Final</Form.Label>
                        <Form.Control type={'time'} name={'horarioFim'} onChange={handleOnChange} value={state?.horarioFim}></Form.Control>
                    </Form.Group>
                    <Button type={'submit'}>Atualizar</Button>
                </Form>
            </div>
        </div>
    )
}
export default HorarioAtendimentoEdit
