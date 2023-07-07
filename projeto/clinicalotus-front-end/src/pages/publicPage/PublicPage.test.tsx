import React from 'react'
import { render, screen } from '@testing-library/react'
import PublicPage from "./PublicPage";


test('renders learn react link', () => {
  render(<PublicPage />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
