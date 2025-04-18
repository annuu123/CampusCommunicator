// Import required modules
const express = require('express');
const cors = require('cors');
const dotenv = require('dotenv');
const mongoose = require('mongoose');
const path = require('path');

// Initialize dotenv to load environment variables from .env file
dotenv.config();

// Create an instance of the Express app
const app = express();

// Middleware setup
app.use(cors()); // For handling cross-origin requests
app.use(express.json()); // To parse incoming JSON requests
app.use(express.urlencoded({ extended: true })); // To parse form data

// Connect to MongoDB (replace with your actual DB URI in .env file)
mongoose.connect(process.env.DB_URI, { 
    useNewUrlParser: true, 
    useUnifiedTopology: true 
})
.then(() => console.log('MongoDB connected'))
.catch(err => console.log('MongoDB connection error:', err));

// API Routes
app.get('/', (req, res) => {
    res.send('Welcome to the Campus Communicator API!');
});

// Add more routes for auth, chat, and notices here
// app.use('/api/auth', require('./auth/authRoutes'));
// app.use('/api/chat', require('./chat/chatRoutes'));
// app.use('/api/notice', require('./notice/noticeRoutes'));

// Serve static files (if any frontend is to be served)
app.use(express.static(path.join(__dirname, 'public')));

// Set the port to listen on
const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});
