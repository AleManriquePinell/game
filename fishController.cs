using Godot;
using System;

public partial class fishController : Spatial
{
    // Movement variables
    private Vector3 currentPosition;
    private Vector3i gridDirection = Vector3i.Forward;

    // Grid variables
    private int gridSizeX = 10;
    private int gridSizeZ = 10;
    private float movementSpeed = 1f;

    // Called every frame. 'delta' is the elapsed time since the previous frame.
    public override void _Process(float delta)
    {
        HandleInput();
        Move();
    }

    // Handle player input for movement
    private void HandleInput()
    {
        // Check for keyboard input
        Vector3 keyboardDirection = Vector3.Zero;

        if (Input.IsActionPressed("ui_right"))
        {
            keyboardDirection.x += 1;
        }
        else if (Input.IsActionPressed("ui_left"))
        {
            keyboardDirection.x -= 1;
        }
        else if (Input.IsActionPressed("ui_down"))
        {
            keyboardDirection.z += 1;
        }
        else if (Input.IsActionPressed("ui_up"))
        {
            keyboardDirection.z -= 1;
        }

        // Normalize the movement direction to ensure diagonal movement is not faster
        keyboardDirection = keyboardDirection.Normalized();

        // Update the grid direction based on the keyboard input
        gridDirection = new Vector3i(Mathf.RoundToInt(keyboardDirection.x), 0, Mathf.RoundToInt(keyboardDirection.z));

        // Check for touch input
        foreach (var @event in GetInputEventStream())
        {
            if (@event is InputEventScreenTouch touchEvent)
            {
                // Get the touch position
                Vector2 touchPosition = touchEvent.Position;

                // Determine which side of the screen was touched
                if (touchPosition.x > GetViewportRect().Size.x / 2)
                {
                    // Right side of the screen was touched, move the fish right
                    gridDirection.x = 1;
                }
                else
                {
                    // Left side of the screen was touched, move the fish left
                    gridDirection.x = -1;
                }
            }
        }
    }

    // Move the fish based on the grid direction
    private void Move()
    {
        // Calculate the target position
        Vector3 targetPosition = currentPosition + gridDirection;

        // Clamp the target position within the grid boundaries
        targetPosition.x = Mathf.Clamp(targetPosition.x, 0, gridSizeX - 1);
        targetPosition.z = Mathf.Clamp(targetPosition.z, 0, gridSizeZ - 1);

        // Move the fish towards the target position
        currentPosition = currentPosition.LinearInterpolate(targetPosition, movementSpeed);
        GlobalTransform = new Transform(Basis.Identity, currentPosition);
    }

    // Called when the node enters the scene tree for the first time.
    public override void _Ready()
    {
        currentPosition = Translation;
    }
}
