import React from 'react'
import { render, screen } from '@testing-library/react'
import Prontuario from "./ProntuarioHistoric";

test('renders learn react link', () => {
  render(<Prontuario />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
