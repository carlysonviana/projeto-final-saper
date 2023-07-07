import React from 'react'
import { render, screen } from '@testing-library/react'
import ConsultaAdd from "./ConsultaAdd";

test('renders learn react link', () => {
  render(<ConsultaAdd />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
