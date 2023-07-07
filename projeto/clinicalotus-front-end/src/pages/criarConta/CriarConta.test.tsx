import React from 'react'
import { render, screen } from '@testing-library/react'
import CriarConta from "./CriarConta";

test('renders learn react link', () => {
  render(<CriarConta />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
