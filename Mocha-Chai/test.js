// install axios, moocha, chai
const axios = require('axios');
const expect = require('chai').expect;

describe('/ route', () => {
    describe('/ GET', () => {
        it('should return 200 status', async () => {
            // Arrange
            const res = await axios.get('http://localhost:8000/')

            // Act
            /// n/a

            // Assert
            expect(res.status).to.equal(200);
        })
    })

    describe('/ POST', () => {
        it('should return 201 status', async () => {
            // Arrange
            const res = await axios.post('http://localhost:8000/')

            // Act
            /// n/a

            // Assert
            expect(res.status).to.equal(201);
        })
    })
})