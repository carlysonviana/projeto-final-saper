/* eslint-disable */
// @ts-nocheck
import React, { useContext, useState, useEffect } from 'react'
import Select from 'react-select';
import { HorarioAtendimentoAddForm } from "../type";
import { Button, Form } from "react-bootstrap";
import useAPI from "../../../service/api";
import { useNavigate } from "react-router-dom";

function HorarioAtendimentoAdd() {
    const [state, setState] = useState<HorarioAtendimentoAddForm>()
    const API = useAPI();
    const [doctors, setDoctors] = useState([])
    const [selectedDoctor, setSelectedDoctor] = useState(null)
    const navigate = useNavigate();

    useEffect(() => {
        onLoad()
    }, [])

    const onLoad = async () => {
        try {
            const responseDoctor = await API.get('funcionario')

            if (responseDoctor) {
                let newDoctorOptions = []
                for (const entry of responseDoctor) {
                    if (entry.categoriaFuncionario_id == 2) {
                        newDoctorOptions.push({ value: entry.id, label: entry.nome });
                    }
                }

                console.log(newDoctorOptions)
                setDoctors(newDoctorOptions)
            }
        } catch (e) {
            alert(e)
        }
    }

    const handleOnChange = (e: any) => {
        setState((state) => ({ ...state, [e.target.name]: e.target.value } as HorarioAtendimentoAddForm))
    }

    const handleSubmit = (e: any) => {
        e.preventDefault();

        if (state) {
            state.horarioInicio = formatHour(state.horarioInicio);
            state.horarioFim = formatHour(state.horarioFim);
            state.medico_id = selectedDoctor.value;
        }



        API.post('horarioAtendimento', state).then(() => {
            alert("Cadastro Criado Com Sucesso!");
            navigate('/horarios');
        });
        console.log(state);
    }
    const formatHour = (time: string | undefined) => {
        if (!time) return '';

        const [hh, mm] = time.split(':');
        return `${hh}:${mm}:00`;
    };

    const onSelectDoctor = (currentDoctor = null) => {
        setSelectedDoctor(currentDoctor);
    }

    return (
        <div className={'card m-auto'}>
            <div className={'card-header'}>
                <h2 className={'card-title'}>Cadastro do Horario</h2>
            </div>
            <div className={'card-body'}>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label>Dia da Semana</Form.Label>
                        <Form.Control as="select" name="diaDaSemana" value={state?.diaDaSemana} onChange={handleOnChange}>
                            <option></option>
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
                        <Form.Label>Hora Final</Form.Label>
                        <Form.Control type={'time'} name={'horarioFim'} onChange={handleOnChange} value={state?.horarioFim}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Medico</Form.Label>
                        <Select
                            placeholder='Selecione uma opção'
                            className='Select'
                            defaultValue={selectedDoctor}
                            onChange={(doctor) => onSelectDoctor(doctor)}
                            options={doctors}
                        />
                    </Form.Group>
                    <Button type={'submit'}>Cadastrar</Button>
                </Form>
            </div>
        </div>
    )
}
export default HorarioAtendimentoAdd