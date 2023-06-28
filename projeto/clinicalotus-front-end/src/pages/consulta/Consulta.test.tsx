import React from 'react'
import { render, screen } from '@testing-library/react'
import Consulta from "./Consulta";

test('renders learn react link', () => {
  render(<Consulta />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
