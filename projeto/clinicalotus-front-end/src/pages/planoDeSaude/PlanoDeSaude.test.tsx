import React from 'react'
import { render, screen } from '@testing-library/react'
import PlanoDeSaude from "./PlanoDeSaude";

test('renders learn react link', () => {
  render(<PlanoDeSaude />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
