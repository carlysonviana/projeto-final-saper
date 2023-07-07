import React, { useContext, useEffect, useState } from 'react'
import { ProntuarioEditForm } from "../type";
import { Button, Form } from "react-bootstrap";
import useAPI from "../../../service/api";
import { useNavigate, useParams } from "react-router-dom";

function ProntuarioEdit() {
    const [state, setState] = useState<ProntuarioEditForm>()
    const API = useAPI();
    const navigate = useNavigate();

    const { id } = useParams();

    useEffect(() => {
        API.get('prontuario/' + id).then(data => {
            console.log(data);
            setState((state) => ({ ...state, diagnostico: data.diagnostico, receituario: data.receituario, paciente_id: data.paciente_id } as ProntuarioEditForm));
        })
    }, [])
    const handleOnChange = (e: any) => {
        setState((state) => ({ ...state, [e.target.name]: e.target.value } as ProntuarioEditForm))
    }

    const handleSubmit = (e: any) => {
        e.preventDefault();

        API.put('prontuario/' + id, state).then(() => {
            navigate('/prontuarios');
        });
        console.log(state);
    }

    return (
        <div className={'card m-auto'}>
            <div className={'card-header'}>
                <h2 className={'card-title'}>Editar Cadastro de Prontuario</h2>
            </div>
            <div className={'card-body'}>
                <Form onSubmit={handleSubmit}>
                    <Form.Group>
                        <Form.Label>Diagnostico</Form.Label>
                        <Form.Control type={'text'} name={'diagnostico'} onChange={handleOnChange} value={state?.diagnostico}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Horario Inicial</Form.Label>
                        <Form.Control type={'text'} name={'receituario'} onChange={handleOnChange} value={state?.receituario}></Form.Control>
                    </Form.Group>

                    <Button type={'submit'}>Atualizar</Button>
                </Form>
            </div>
        </div>
    )
}
export default ProntuarioEdit
