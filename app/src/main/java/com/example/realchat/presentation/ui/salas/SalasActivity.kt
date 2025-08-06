package com.example.realchat.presentation.ui.salas


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realchat.R
import com.example.realchat.presentation.adapter.SalasAdapter
import com.example.realchat.presentation.ui.chat.ChatActivity
import com.example.realchat.presentation.ui.perfil.PerfilActivity
import com.example.realchat.presentation.viewmodel.SalasViewModel
import com.example.realchat.utils.Constants
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Actividad que muestra la lista de salas de chat y permite crear nuevas.
 */
@AndroidEntryPoint
class SalasActivity : AppCompatActivity() {

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    private val salasViewModel: SalasViewModel by viewModels()
    private lateinit var salasAdapter: SalasAdapter
    private lateinit var createRoomNameEditText: EditText
    private lateinit var createRoomButton: Button
    private lateinit var profileButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_salas)
        val mainContainer = findViewById<View>(R.id.salas_activity_xml) // Obtén tu contenedor principal

        ViewCompat.setOnApplyWindowInsetsListener(mainContainer) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Aplica padding a tu contenedor principal para evitar las barras del sistema
            view.updatePadding(
                left = insets.left,
                top = insets.top,
                right = insets.right,
                bottom = insets.bottom
            )

            // Devuelve los insets consumidos o los originales si no los consumes completamente
            WindowInsetsCompat.CONSUMED // O puedes devolver windowInsets si otras vistas también necesitan procesarlos
        }

        createRoomNameEditText = findViewById(R.id.createRoomNameEditText)
        createRoomButton = findViewById(R.id.createRoomButton)
        profileButton = findViewById(R.id.profileButton)
        val recyclerView: RecyclerView = findViewById(R.id.salasRecyclerView)

        salasAdapter = SalasAdapter { chatRoom ->
            // Maneja el clic en una sala de chat.
            val intent = Intent(this, ChatActivity::class.java).apply {
                putExtra(Constants.EXTRA_ROOM_ID, chatRoom.id)
                putExtra(Constants.EXTRA_ROOM_NAME, chatRoom.name)
            }
            startActivity(intent)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SalasActivity)
            adapter = salasAdapter
        }

        // Observa la lista de salas de chat.
        lifecycleScope.launch {
            salasViewModel.chatRooms.collect { chatRooms ->
                salasAdapter.submitList(chatRooms)
            }
        }

        // Observa los eventos del ViewModel (errores, éxito).
        lifecycleScope.launch {
            salasViewModel.eventFlow.collect { event ->
                when (event) {
                    is SalasViewModel.UiEvent.ShowToast -> {
                        Toast.makeText(this@SalasActivity, event.message, Toast.LENGTH_SHORT).show()
                    }
                    SalasViewModel.UiEvent.RoomCreated -> {
                        createRoomNameEditText.text.clear()
                        Toast.makeText(this@SalasActivity, "Sala creada con éxito!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        createRoomButton.setOnClickListener {
            val userName = "Usuario"
            val roomName = createRoomNameEditText.text.toString().trim()
            if (roomName.isNotEmpty()) {
                salasViewModel.createChatRoom(roomName)
            } else {
                Toast.makeText(this, "El nombre de la sala no puede estar vacío.", Toast.LENGTH_SHORT).show()
            }
        }

        profileButton.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Registra la pantalla cuando la actividad se vuelve visible
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, "Salas de Chat")
            putString(FirebaseAnalytics.Param.SCREEN_CLASS, "SalasActivity")
        })
    }
}

