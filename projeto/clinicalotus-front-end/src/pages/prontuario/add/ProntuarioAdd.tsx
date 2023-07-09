/* eslint-disable */
// @ts-nocheck
import React, { useContext, useState, useEffect } from 'react'
import Select from 'react-select';
import { ProntuarioAddForm } from "../type";
import { Button, Form } from "react-bootstrap";
import useAPI from "../../../service/api";
import { useNavigate } from "react-router-dom";

function ProntuarioAdd() {
    const [state, setState] = useState<ProntuarioAddForm>()
    const API = useAPI();
    const [pacientes, setPacientes] = useState([])
    const [selectedPaciente, setSelectedPaciente] = useState(null)
    const navigate = useNavigate();

    useEffect(() => {
        onLoad()
    }, [])

    const onLoad = async () => {
        try {
            const responsePaciente = await API.get('paciente')

            if (responsePaciente) {
                let newPacienteOptions = []
                for (const entry of responsePaciente) {
                    newPacienteOptions.push({ value: entry.id, label: entry.nome });

                }

                console.log(newPacienteOptions)
                setPacientes(newPacienteOptions)
            }
        } catch (e) {
            alert(e)
        }
    }

    const handleOnChange = (e: any) => {
        setState((state) => ({ ...state, [e.target.name]: e.target.value } as ProntuarioAddForm))
    }

    const handleSubmit = (e: any) => {
        e.preventDefault();

        if (state) state.paciente_id = selectedPaciente.value;




        API.post('prontuario', state).then(() => {
            alert("Cadastro Criado Com Sucesso!");
            navigate('/prontuarios');
        });
        console.log(state);
    }

    const onSelectPaciente = (currentPaciente = null) => {
        setSelectedPaciente(currentPaciente);
    }

    return (
        <div className={'card m-auto'}>
            <div className={'card-header'}>
                <h2 className={'card-title'}>Cadastro do Prontuario</h2>
            </div>
            <div className={'card-body'}>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label>Diagnóstico</Form.Label>
                        <textarea className={"form-control"} name="diagnostico" onChange={handleOnChange} value={state?.diagnostico} rows={4}></textarea>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Receituário</Form.Label>
                        <textarea className={"form-control"} name="receituario" onChange={handleOnChange} value={state?.receituario} rows={4}></textarea>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Paciente</Form.Label>
                        <Select
                            placeholder='Selecione uma opção'
                            className='Select'
                            defaultValue={selectedPaciente}
                            onChange={(paciente) => onSelectPaciente(paciente)}
                            options={pacientes}
                        />
                    </Form.Group>
                    <Button type={'submit'}>Cadastrar</Button>
                </Form>
            </div>
        </div>
    )
}
export default ProntuarioAdd